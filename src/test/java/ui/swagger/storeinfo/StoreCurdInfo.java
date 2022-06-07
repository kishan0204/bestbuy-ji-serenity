package ui.swagger.storeinfo;
import ui.swagger.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class StoreCurdInfo extends TestBase {
    static String name = "Ramesh";
    static String type = "xyz";
    static String address = "bunny";
    static String address2 = "123";
    static String city = "hgj";
    static String state = "asd";
    static String zip = "1234";
    static int lat = 25;
    static int lng = 37;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;

    @Steps
    StoreSteps storeSteps;

    @Title("create a store detail")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.creatStore(name, type, address, address2, city, state,
                zip, lat, lng, hours);
        response.log().all().statusCode(201);
        storeId = response.extract().path("id");
        System.out.println(storeId);
    }

    @Title("Get single store detail")
    @Test
    public void test002() {
        ValidatableResponse response= storeSteps.getStoreById(storeId);
        response.log().all().body("name",equalTo(name));
    }
    @Title("this will update store detail")
    @Test
    public void test003(){
        name=name+"Kothari";
        ValidatableResponse response= storeSteps.updateStoreDetail(storeId,name, type, address, address2, city, state,
                zip, lat, lng, hours);
        response.log().all().statusCode(200);


    }
    @Title("this will delete store detail")
    @Test
    public void test004(){
        ValidatableResponse response= storeSteps.deletStoreById(storeId);
        response.log().all().statusCode(200);

        ValidatableResponse response1= storeSteps.getStoreById(storeId);
        response1.log().all().statusCode(404);
    }
}
