package com.springboot.httpResponse.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="mobiles")
public class Mobiles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;

	@NotNull(message = "mobile name not be null")
	@Size(min = 4)
	private String mobileName;
	@NotNull(message = "model not be empty")
	@Size(min = 1)
	private String model;

	@NotNull(message = "price not be empty")
	private Integer price;
}
