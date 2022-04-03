package dmit2015.jdelcolle1.assignment07.view;

import dmit2015.jdelcolle1.assignment07.restclient.Bill;
import dmit2015.jdelcolle1.assignment07.restclient.BillService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.Optional;

@Named("currentBillDeleteController")
@ViewScoped
public class BillDeleteController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private BillService _billService;

    @Inject
    @ManagedProperty("#{param.editId}")
    @Getter
    @Setter
    private String editId;

    @Getter
    private Bill existingBill;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            existingBill = _billService.findById(editId);
            if (existingBill == null) {
                Faces.redirect(Faces.getRequestURI().substring(0, Faces.getRequestURI().lastIndexOf("/")) + "/index");
            }
        }
    }

    public String onDelete() {
        String nextPage = "";
        try {
            _billService.delete(editId);
            Messages.addFlashGlobalInfo("Delete was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Delete not successful.");
        }
        return nextPage;
    }
}