package com.expense.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Claim type cannot be blank")
    @Size(max = 50, message = "Claim type must be at most 50 characters")
    @Column(name = "claim_type")
    private String claimType;

    @NotNull(message = "Date of expense cannot be null")
    @Column(name = "date_of_expense")
    private LocalDate dateOfExpense;

    @Positive(message = "Amount must be positive")
    @Column(name = "amount")
    private double amount;

    @NotBlank(message = "Claim month cannot be blank")
    @Size(max = 10, message = "Claim month must be at most 10 characters")
    @Column(name = "claim_month")
    private String claimMonth;

    @Min(value = 1900, message = "Claim year must be at least 1900")
    @Column(name = "claim_year")
    private int claimYear;

    @NotBlank(message = "Status cannot be blank")
    @Size(max = 10, message = "Status must be at most 10 characters")
    @Column(name = "status")
    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public LocalDate getDateOfExpense() {
		return dateOfExpense;
	}

	public void setDateOfExpense(LocalDate dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getClaimMonth() {
		return claimMonth;
	}

	public void setClaimMonth(String claimMonth) {
		this.claimMonth = claimMonth;
	}

	public int getClaimYear() {
		return claimYear;
	}

	public void setClaimYear(int claimYear) {
		this.claimYear = claimYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Expense(Long id,
			@NotBlank(message = "Claim type cannot be blank") @Size(max = 50, message = "Claim type must be at most 50 characters") String claimType,
			@NotNull(message = "Date of expense cannot be null") LocalDate dateOfExpense,
			@Positive(message = "Amount must be positive") double amount,
			@NotBlank(message = "Claim month cannot be blank") @Size(max = 10, message = "Claim month must be at most 10 characters") String claimMonth,
			@Min(value = 1900, message = "Claim year must be at least 1900") int claimYear,
			@NotBlank(message = "Status cannot be blank") @Size(max = 10, message = "Status must be at most 10 characters") String status) {
		super();
		this.id = id;
		this.claimType = claimType;
		this.dateOfExpense = dateOfExpense;
		this.amount = amount;
		this.claimMonth = claimMonth;
		this.claimYear = claimYear;
		this.status = status;
	}

    // getters and setters
}
