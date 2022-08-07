package com.udemy.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.udemy.hrpayroll.entities.Payment;
import com.udemy.hrpayroll.entities.Worker;

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	//Usaremos o componente RestTemplate para fazer requisição para o projeto hr-worker;
	@Autowired
	private RestTemplate restTemplate;
	
	public Payment getPayment(long workerId, int days) {
		//Iremos atribuir para a chave id o valor passado na requisição do PaymentResource (workerId):
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", ""+workerId); 
		
		//Após atribuído o valor para o "id", podemos usar essa chave para fazer a requisição dos dados do worker no hr-worker:
		Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
		
		//Por fim executar o serviço do hr-payroll em cima da entidade recebida do hr-worker;
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
