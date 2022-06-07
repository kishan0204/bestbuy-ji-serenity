package ui.swagger.serviceinfo;
import ui.swagger.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SerenityRunner.class)
public class ServiceCurdInfo extends TestBase {

    static String name = "Ramesh";
    static int serviceId;

    @Steps
    ServiceSteps serviceSteps;

    @Title("this will create service")
    @Test
    public void test001() {
        ValidatableResponse response = serviceSteps.createService(name);
        response.log().all().statusCode(201);
        serviceId = response.extract().path("id");
        System.out.println(serviceId);
    }

    @Title("This will fatch single service")
    @Test
    public void test002() {
        ValidatableResponse response = serviceSteps.getSingleService(serviceId);
        response.log().all().statusCode(200);

        response.body("name", equalTo(name));
    }

    @Title("This will update service")
    @Test
    public void test003(){
        name= name+"Kothari";
        ValidatableResponse response= serviceSteps.updateService(serviceId,name);
        response.log().all().statusCode(200);
    }

    @Title("This will delete the service")
    @Test
    public void test004(){
        ValidatableResponse response= serviceSteps.deleteServiceByid(serviceId);
        response.log().all().statusCode(200);

        ValidatableResponse response1= serviceSteps.getSingleService(serviceId);
        response1.log().all().statusCode(404);
    }

}

