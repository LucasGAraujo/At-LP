package PetFriends_Almoxarifado.repository;

import PetFriends_Almoxarifado.domain.OrdemDePreparacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdemDePreparacaoRepository extends JpaRepository<OrdemDePreparacao, Long> {

    Optional<OrdemDePreparacao> findByPedidoId(Long pedidoId);
}