package br.com.aula.fullstack.controller;

import br.com.aula.fullstack.model.Pessoa;
import br.com.aula.fullstack.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    // Listagem para consultar as pessoas cadastradas.
    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    // Cadastro incluído para permitir testar a lista e os endpoints de atualização.
    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa) {
        pessoa.setId(null);
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    // PUT substitui os dados da pessoa: neste exercício, o único dado é o nome.
    @PutMapping("/{id}")
    public Pessoa substituir(@PathVariable Long id, @Valid @RequestBody Pessoa dados) {
        Pessoa pessoa = buscarPessoa(id);
        pessoa.setNome(dados.getNome());
        return pessoaRepository.save(pessoa);
    }

    // PATCH altera somente os campos enviados no corpo da requisição.
    @PatchMapping("/{id}")
    public Pessoa atualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        Pessoa pessoa = buscarPessoa(id);

        if (campos.containsKey("nome")) {
            Object novoNome = campos.get("nome");
            if (!(novoNome instanceof String) || ((String) novoNome).isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "O campo 'nome' deve ser um texto não vazio.");
            }
            pessoa.setNome((String) novoNome);
        }

        if (campos.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Informe ao menos um campo para atualizar.");
        }

        return pessoaRepository.save(pessoa);
    }

    // DELETE remove a pessoa e retorna 204 sem corpo de resposta.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Pessoa pessoa = buscarPessoa(id);
        pessoaRepository.delete(pessoa);
        return ResponseEntity.noContent().build();
    }

    private Pessoa buscarPessoa(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pessoa não encontrada para o ID " + id + "."));
    }
}
