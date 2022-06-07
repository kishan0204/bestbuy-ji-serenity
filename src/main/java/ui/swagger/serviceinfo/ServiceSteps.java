package ui.swagger.serviceinfo;
import ui.swagger.model.ServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import ui.swagger.constants.services.EndPoints;



public class ServiceSteps {

    @Step
    public ValidatableResponse createService(String name){
        ServicePojo servicePojo= new ServicePojo();
        servicePojo.setName(name);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicePojo)
                .when()
                .post(EndPoints.CREATE_SERVICE)
                .then();

    }
    @Step
    public ValidatableResponse getSingleService(int serviceId){
        return SerenityRest.given().log().all()
                .pathParam("serviceID",serviceId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then();
    }
    @Step
    public ValidatableResponse updateService(int serviceId,String name) {
        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("serviceID", serviceId)
                .body(servicePojo)
                .when()
                .patch(EndPoints.UPDATE_SERVICE_BY_ID)
                .then();
    }
        @Step
        public ValidatableResponse deleteServiceByid(int serviceId){
            return SerenityRest.given().log().all()
                    .pathParam("serviceID",serviceId)
                    .when()
                    .delete(EndPoints.DELETE_SERVICE_BY_ID)
                    .then();
        }
    }