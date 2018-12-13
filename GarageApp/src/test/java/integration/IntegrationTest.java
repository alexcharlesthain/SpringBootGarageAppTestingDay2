package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qa.thain.alex.garage.app.GarageAppApplication;
import com.qa.thain.alex.garage.app.model.GarageAppModel;
import com.qa.thain.alex.garage.app.repository.GarageAppRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GarageAppApplication.class})
@AutoConfigureMockMvc

public class IntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private GarageAppRepository repository;
	
	//@Before
	//public void clearDB() {
		//repository.deleteAll(); 
	//}
	
	@Test
	public void findingAndRetrievingVehicleFromDatabase() throws Exception {
		repository.save(new GarageAppModel("Boat", "OceanG", "Roger", "B04T4", 40));
		mvc.perform(get("/api/vehicle")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].vType", is("Boat")));
	}
	
	@Test
	public void addAVehicleToDatabaseTest() throws Exception {
		mvc.perform(post("/api/vehicle")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"vType\" : \"Car\", \"vMake\" : \"BMW\", \"vModel\" : \"1SERIES\", \"vRegistrationNumber\" : \"DS18YUU}"))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.vType", is("Car")));
	}
	
	@Test
	public void updateVehicleTest() throws Exception {
		mvc.perform(put("/api/vehicle")
				.contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void deleteVehicleTest() throws Exception {
		mvc.perform(delete("/api/vehicle{vRegistrationNumber}", "B04T3")
				.contentType(MediaType.APPLICATION_JSON));	
	}
}
