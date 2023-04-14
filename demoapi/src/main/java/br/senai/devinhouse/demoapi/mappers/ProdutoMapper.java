package br.senai.devinhouse.demoapi.mappers;

import br.senai.devinhouse.demoapi.dtos.ProdutoDetalhadoDTO;
import br.senai.devinhouse.demoapi.dtos.ProdutoRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoResponse;
import br.senai.devinhouse.demoapi.models.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProdutoMapper {

    @Mapping(target = "categoria.id", source = "categoria_id")
    Produto map(ProdutoRequest source);

    @Mapping(target = "quantidadeEstoque", source = "qtdEstoque")
    ProdutoResponse map(Produto source);

    List<ProdutoResponse> map(List<Produto> source);

    @Mapping(target = "categoria.id", source = "categoria_id")
    Produto map(ProdutoDetalhadoDTO source);

    default Page<ProdutoResponse> map(Page<Produto> source) {
        return source.map(this::map);
    }





    /*public static Produto map(ProdutoRequest source) {
         if (source == null) {
           return null;
         }
        Produto target = new Produto();


        target.setNome(source.getNome());
        target.setDescricao(source.getDescricao());
        target.setPreco(source.getPreco());
        target.setQtdEstoque(source.getQtdEstoque());

        return target;
    }*/
    /*
    public static ProdutoResponse map(Produto source) {
        ProdutoResponse target = new ProdutoResponse();

        target.setNome(source.getNome());
        target.setDescricao(source.getDescricao());
        target.setPreco(source.getPreco());
        target.setQtdEstoque(source.getQtdEstoque());
        target.setCategoria(source.getCategoria());

        return target;
    }*/


}
