package model;

import javax.persistence.*;

@Entity
@Table(name = "soldCars")
public class SoldCars {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Long price;

    @Column(name = "date")
    private Long date;

    public SoldCars() {
    }

    public SoldCars(Long price, Long date) {
        this.price = price;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() { return price; }

    public void setPrice(Long price) { this.price = price; }

    public Long getDate() { return date; }

    public void setDate(Long date) { this.date = date; }

}
