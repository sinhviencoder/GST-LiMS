package com.lims.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.lims.entity.RequestBuyBook;
import com.lims.repository.RequestBuyBookRepository;
import com.lims.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService{
	@Autowired
	RequestBuyBookRepository requestRepository;
	@Override
	public Iterable<RequestBuyBook> getRequestBuyBookdAll() {
		return requestRepository.findAll();
	}

	@Override
	public RequestBuyBook save(RequestBuyBook request) {
		request.setStatus(1);
		return requestRepository.save(request);
	}

	@Override
	public void delete(long id) {
		requestRepository.deleteById(id);
		
	}

	@Override
	public Optional<RequestBuyBook> getRequestBuyBookById(long id) {
		
		return requestRepository.findById(id);
	}

	

	@Override
	public DataTablesOutput<RequestBuyBook> getRequestBuyBookAll(DataTablesInput input) {
		return requestRepository.findAll(input);
	}

	@Override
	public List<RequestBuyBook> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestBuyBook updateRequestByID(RequestBuyBook request) {
		return requestRepository.save(request);
	}

}
