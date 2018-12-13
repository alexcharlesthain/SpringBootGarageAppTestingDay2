package repo;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.qa.thain.alex.garage.app.GarageAppApplication;
import com.qa.thain.alex.garage.app.model.GarageAppModel;
import com.qa.thain.alex.garage.app.repository.GarageAppRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GarageAppApplication.class })
@SpringBootTest(classes = { GarageAppApplication.class })
@DataJpaTest
public class RepositoryTest {
 
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private GarageAppRepository myRepository;
	
	@Test
	public void retrieveByIdTest() {
		GarageAppModel model1 = new GarageAppModel("Car", "Bentley", "GT", "FD12HUY", 39);
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(myRepository.findById(model1.getId()).isPresent());
	}
}
