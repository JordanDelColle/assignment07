package dmit2015.jdelcolle1.assignment07.view;

import dmit2015.jdelcolle1.assignment07.restclient.Bill;
import dmit2015.jdelcolle1.assignment07.restclient.BillService;
import lombok.Getter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("currentBillListController")
@ViewScoped
public class BillListController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    @RestClient
    private BillService _billService;

    @Getter
    private Map<String, Bill> billMap;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            billMap = _billService.list();
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}