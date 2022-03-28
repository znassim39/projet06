package com.salimahafirassou.paymybuddy.domain;

import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name="buddy",
	uniqueConstraints = {
		@UniqueConstraint(name = "buddy_unique", columnNames = {"idUser", "idBuddy"})
	}
)
public class Buddy {

    @Id
    @GeneratedValue(
            strategy = AUTO
    )
	@Column(name="id")
	private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
	@JoinColumn(
            name = "idUser"
    )
	private UserEntity user;
	
	@ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
	@JoinColumn(
            name = "idBuddy"
    )
	private UserEntity buddy;
    
}
