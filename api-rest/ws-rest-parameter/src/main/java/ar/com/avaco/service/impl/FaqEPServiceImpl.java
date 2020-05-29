/**
 * 
 */
package ar.com.avaco.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import ar.com.avaco.commons.domain.Faq;
import ar.com.avaco.commons.service.FaqService;
import ar.com.avaco.service.FaqEPService;
import ar.com.avaco.ws.dto.FaqDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

/**
 * @author avaco
 */

@Service("faqEPService")
public class FaqEPServiceImpl extends CRUDEPBaseService<Long, FaqDTO, Faq, FaqService> implements FaqEPService {
	
	@Override
	@Resource(name = "faqService")
	protected void setService(FaqService faqService) {
		this.service = faqService;
		
	}
	
	@Override
	public Faq convertToEntity(FaqDTO dto) {
		Faq entity = new Faq();
		entity.setId(dto.getId());
		entity.setOrderFaq(dto.getOrder());
		entity.setFavourite(dto.getFavourite());
		entity.setLang(dto.getLang());
		entity.setCategory(dto.getCategory());
		entity.setSubcategory(dto.getSubcategory());
		entity.setQuestion(dto.getQuestion());
		entity.setAnswer(dto.getAnswer());
		return entity;
	}

	@Override
	public FaqDTO convertToDto(Faq entity) {
		FaqDTO dto = new FaqDTO();
		dto.setId(entity.getId());
		dto.setOrder(entity.getOrderFaq());
		dto.setFavourite(entity.getFavourite());
		dto.setLang(entity.getLang());
		dto.setCategory(entity.getCategory());
		dto.setSubcategory(entity.getSubcategory());
		dto.setQuestion(entity.getQuestion());
		dto.setAnswer(entity.getAnswer());
		return dto;
	}

	@Override
	public List<FaqDTO> listFaqs(String category, String subcategory) {
		List<Faq> listFaqs = service.listFaqs(category, subcategory);
		List<FaqDTO> dtos = convertToDtos(listFaqs);
		listFaqs = null;
		return dtos;
	}
	
	@Override
	public List<FaqDTO> listFaqs(String category) {
		List<Faq> listFaqs = service.listFaqs(category);
		List<FaqDTO> dtos = convertToDtos(listFaqs);
		listFaqs = null;
		return dtos;
	}
	
	
		
}
