package PetFriends_Almoxarifado.service;

import PetFriends_Almoxarifado.DTO.PedidoProntoParaSeparacaoDTO;
import PetFriends_Almoxarifado.domain.CodigoDeBarras;
import PetFriends_Almoxarifado.domain.OrdemDePreparacao;
import PetFriends_Almoxarifado.repository.OrdemDePreparacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlmoxarifadoService {

    private final OrdemDePreparacaoRepository repository;

    public AlmoxarifadoService(OrdemDePreparacaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void iniciarSeparacao(PedidoProntoParaSeparacaoDTO payload) {

        Long idDoPedido = Long.valueOf(payload.pedidoId());

        OrdemDePreparacao novaOrdem = new OrdemDePreparacao(idDoPedido);

        for (var itemDTO : payload.itens()) {

            CodigoDeBarras codigo = new CodigoDeBarras(itemDTO.codigoDeBarras());

            novaOrdem.adicionarItem(codigo, itemDTO.quantidade());
        }

        repository.save(novaOrdem);

        System.out.println("✅ Ordem de Preparação do pedido " + idDoPedido + " salva no banco com sucesso!");
    }
}