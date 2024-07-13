import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/soap")
public class SoapClientResource {

    @Inject
    Logger logger;

    /*
    *
    * 1. YourServicePortType 接口和相關的類將由 CXF 插件根據您的 WSDL 文件自動生成。確保 WSDL 文件放在正確的位置（src/main/resources/wsdl/your-service.wsdl）。
    * 2. 需要根據實際的 SOAP 服務接口調整 yourServiceMethod 的名稱和參數。
    * 3. quarkus.cxf.client.yourServiceClient.client-endpoint-url 應該設置為實際的 SOAP 服務端點 URL。
    * 4. quarkus.cxf.client.yourServiceClient.service-interface 應該設置為由 WSDL 生成的服務接口的全限定名稱。
    * 5. 如果 SOAP 服務需要認證，您需要在配置中添加相應的用戶名和密碼。
    * 6. 對於複雜的 SOAP 請求和響應，您可能需要創建額外的類來處理數據的序列化和反序列化。
    *
    * */

    @CXFClient("yourServiceClient")
    /*
     * 這裡的 YourServicePortType 是由 CXF 插件根據您的 WSDL 文件自動生成的接口
     * 我隨便先寫一個class讓程式可以編譯
     */
     YourServicePortType client;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String callSoapService() {
        logger.info("Calling SOAP service");
        try {
            // 這裡調用 SOAP 服務的方法。方法名稱和參數需要根據您的 WSDL 定義來調整
            String result = client.yourServiceMethod("Some parameter");
            logger.info("Successfully received data from SOAP service");
            return "{\"result\": \"" + result + "\"}";
        } catch (Exception e) {
            logger.error("Error calling SOAP service", e);
            return "{\"error\": \"Error calling SOAP service: " + e.getMessage() + "\"}";
        }
    }
}

// 注意：YourServicePortType 接口將由 CXF 插件根據您的 WSDL 文件自動生成