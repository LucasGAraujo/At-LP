package PetFriends_Transporte.service;

import PetFriends_Transporte.DTO.PedidoDespachadoDTO;
import org.springframework.stereotype.Service;

@Service
public class TransporteService {

    public void roteirizarEntrega(PedidoDespachadoDTO payload) {
        System.out.println("==========================================");
        System.out.println("🚛 INICIANDO TRANSPORTE DO PEDIDO: " + payload.pedidoId());
        System.out.println("📦 Total de volumes (caixas): " + payload.volumes());
        System.out.println("⏳ Data Limite de Entrega: " + payload.dataLimiteEntrega());

        System.out.println("👤 Destinatário:");
        System.out.println("   Nome: " + payload.destinatario().nome());
        System.out.println("   Contato: " + payload.destinatario().documentoRecebedor());

        System.out.println("📍 Endereço de Roteirização:");
        System.out.println("   " + payload.enderecoEntrega().logradouro() + " - " + payload.enderecoEntrega().bairro());
        System.out.println("   CEP: " + payload.enderecoEntrega().cep() + " | " + payload.enderecoEntrega().cidade() + "/" + payload.enderecoEntrega().estado());
        System.out.println("==========================================");
    }
}