package com.rsecinformation.cursomc.services;


import com.rsecinformation.cursomc.dto.ClienteDTO;
import com.rsecinformation.cursomc.dto.ClienteNewDTO;
import com.rsecinformation.cursomc.entities.Cidade;
import com.rsecinformation.cursomc.entities.Cliente;
import com.rsecinformation.cursomc.entities.Endereco;
import com.rsecinformation.cursomc.entities.enums.Perfil;
import com.rsecinformation.cursomc.entities.enums.TipoCliente;
import com.rsecinformation.cursomc.repositories.ClienteRepository;
import com.rsecinformation.cursomc.repositories.EnderecoRepository;
import com.rsecinformation.cursomc.security.UserSS;
import com.rsecinformation.cursomc.services.exceptions.AuthorizationException;
import com.rsecinformation.cursomc.services.exceptions.DataIntegrityException;
import com.rsecinformation.cursomc.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private S3Service s3Service;

    public Cliente findById(Integer id) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! ID: " + id + ". " + "Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Integer id, Cliente obj) {
        try {
            Cliente entity = clienteRepository.getById(id);
            updateData(entity, obj);
            return clienteRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Integer id) {
        try {
            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente por que há pedidos relacionados");
        }
    }

    private void updateData(Cliente entity, Cliente obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());

    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()), passwordEncoder.encode(objDto.getSenha()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }

        URI uri =  s3Service.uploadFile(multipartFile);

        Cliente cli = clienteRepository.findById(user.getId());
        cli.setImageUrl(uri.toString());
        clienteRepository.save(cli);

        return uri;
    }
}
