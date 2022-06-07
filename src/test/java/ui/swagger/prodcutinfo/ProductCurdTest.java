package ui.swagger.prodcutinfo;
import ui.swagger.testbase.TestBase;
import ui.swagger.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class ProductCurdTest extends TestBase {

    static String name = "Treadmill" + TestUtils.getRandomValue();
    static String type = "HardGood";
    static int price = 17;
    static int shipping = 2;
    static String upc = "044563424019";
    static String description = "Compatible with any body types and electronic devices; 240V size;";
    static String manufacturer = "PureGym";
    static String model = "MN2400DBZ ";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900."+"p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "https://static.wixstatic.com/media/6011fb_b48414e4c7f446c4a90e02e04056f9cf~mv2.png/v1/fill"+"/w_1362,h_1368,al_c,q_90,enc_auto/BeHealthy.png";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("this will create new product ")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc,
                description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId = response.extract().path("id");
        System.out.println(productId);
    }

    @Title("This will Get the product by id ")
    @Test
    public void test002() {
        ValidatableResponse response = productSteps.getProductListById(productId);
        response.log().all().body("name", equalTo(name));
    }

    @Title("this will update  product ")
    @Test
    public void test003() {
        name = name + "Softwood1147";
        ValidatableResponse response = productSteps.updateProduct(productId, name, type, price, shipping, upc,
                description, manufacturer, model, url, image);
        response.log().all().statusCode(200);

        ValidatableResponse response1 = productSteps.getProductListById(productId);
        response1.log().all().body("name", equalTo(name));
    }

    @Title("This will delete product ")
    @Test
    public void test004() {
        ValidatableResponse response = productSteps.deleteProductById(productId);
        response.log().all().statusCode(200);

        ValidatableResponse response1 = productSteps.getProductById(productId);
        response1.log().all().statusCode(404);
    }

}