/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package space.ankan.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import space.ankan.JokeTeller;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.ankan.space",
                ownerName = "backend.builditbigger.ankan.space",
                packagePath = ""
        )
)
public class MyEndpoint {

    private JokeTeller joker;

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "getGoodJoke")
    public MyBean getGoodJoke() {
        MyBean response = new MyBean();
        if (joker == null)
            joker = new JokeTeller(false);
        response.setData(joker.nextJoke());

        return response;
    }

}
