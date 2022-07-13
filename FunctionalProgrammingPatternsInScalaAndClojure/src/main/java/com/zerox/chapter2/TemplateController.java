package com.zerox.chapter2;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:07
 */
public abstract class TemplateController implements Controller {
    private View view;
    public TemplateController(View view) {
        this.view = view;
    }

    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        Integer responseCode = 200;
        String responseBody = "";

        try {
            Map<String, List<String>> model = doRequest(request);
            responseBody = view.render(model);
        }catch (ControllerException e) {
            responseCode = e.getStatusCode();
        }catch(RenderingException e){
            responseCode = 500;
            responseBody = "Exception while rendering";
        }catch (Exception e) {
            responseCode = 500;
        }

        return HttpResponse.Builder.newBuilder()
                .body(responseBody)
                .responseCode(responseCode)
                .build();
    }

    protected abstract Map<String, List<String>> doRequest(HttpRequest request);
}
