package br.com.api.produtos.servico;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.Produtorepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServico {

  @Autowired
  private Produtorepositorio pr;

  @Autowired
  private RespostaModelo rm;

  // Método para listar os produtos
  public Iterable<ProdutoModelo> listar() {
    return pr.findAll();
  }

  // Método para cadastrar ou alterar produto
  public ResponseEntity<?> cadastrarAndAlterar(ProdutoModelo pm, String acao) {
    if (pm.getNome().equals("")) {
      rm.setMensagem("O nome do produto é obrigatório!");
      return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
    } else if (pm.getMarca().equals("")) {
      rm.setMensagem("O nome da marca é obrigatório!");
      return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
    } else {
      if (acao.equals("cadastrar")) {
        return new ResponseEntity<ProdutoModelo>(
          pr.save(pm),
          HttpStatus.CREATED
        );
      } else {
        return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);
      }
    }
  }
  //Commit sabado

  // Método para remover produtos
  public ResponseEntity<RespostaModelo> remover(Long codigo) {
    pr.deleteById(codigo);
    rm.setMensagem("O produto foi removido com sucesso");
    return new ResponseEntity<>(rm, HttpStatus.OK);
  }
}
