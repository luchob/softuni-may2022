package bg.softuni.mobilele.model.dto.offer;

import javax.validation.constraints.NotEmpty;

public class SearchOfferDTO {
    @NotEmpty
    private String query;

    public SearchOfferDTO() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
