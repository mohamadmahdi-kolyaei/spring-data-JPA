package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        name = "student_id_card_number_unique",
//                        columnNames = "card_number"
//                )
//        }
)
public class StudentIdCard {
    @Id
    @GeneratedValue
    @Column(
            updatable = false
    )
    private Long id;
    @Column(
//            unique = true,
            nullable = false,
            length = 15
    )
    private String cardNumber;
    @OneToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER  // default for one to one is eager , when you load studentIdCard it also fetches the student , lazy : when you load studentIdCard you don't load the student
    ) //  cascade : this allows me to save student when save the card , all means all operation .
    // whenever you're making a change to owning entity in here studentIdCard you also have  all of below to take effect on the entity in our case student
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_id_fk")    // change the foreign key name in database
    )
    private Student student;

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentIdCard() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }



    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }
}
