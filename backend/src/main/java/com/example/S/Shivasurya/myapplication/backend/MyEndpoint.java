/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.S.Shivasurya.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.jokejava.lib.jokeTelling;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.Shivasurya.S.example.com",
    ownerName = "backend.myapplication.Shivasurya.S.example.com",
    packagePath=""
  )
)
public class MyEndpoint {

    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        jokeTelling obj = new jokeTelling();
        MyBean response = new MyBean();
        response.setData(obj.getRandomJoke());
        return response;
    }

}
