package guru.springframework.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import static org.mockito.Mockito.*;
public class ProductControllerTest {
	@Mock
	ProductService productService;

	ProductController controller;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller=new ProductController(productService);
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getProductTest() throws Exception {
		Product pro=new Product();
		pro.setId(ObjectId.createFromLegacyFormat(1, 1, 1));
		//		assertEquals(ObjectId.createFromLegacyFormat(1, 1, 1), 12);

		when(productService.getById(anyString())).thenReturn(pro);

		mockMvc.perform(get("/product/show/"+ObjectId.createFromLegacyFormat(1, 1, 1)))
		.andExpect(view().name("product/show"))
		.andExpect(model().attributeExists("product"));
	}

}
