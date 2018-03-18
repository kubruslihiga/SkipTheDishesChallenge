package br.challenge.skipthedishes.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "std_order")
public class Order extends BasicEntity {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "create_user_id")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = false)
	private User user;

	@Column(name = "code")
	private String code;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Product.class)
	@JoinTable(name = "std_order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	@NotEmpty
	private List<Product> products = new ArrayList<>();

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "created_time")
	@JsonSerialize(using = JsonDatetimeSerializer.class)
	@JsonDeserialize(using = JsonDatetimeDeserializer.class)
	private Date createdTime;

	@Column(name = "canceled_time")
	@JsonSerialize(using = JsonDatetimeSerializer.class)
	@JsonDeserialize(using = JsonDatetimeDeserializer.class)
	private Date canceledTime;

	@JoinColumn(name = "cancel_user_id")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User cancelUser;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getCanceledTime() {
		return canceledTime;
	}

	public void setCanceledTime(Date canceledTime) {
		this.canceledTime = canceledTime;
	}

	public User getCancelUser() {
		return cancelUser;
	}
	
	public void setCancelUser(User cancelUser) {
		this.cancelUser = cancelUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cancelUser == null) ? 0 : cancelUser.hashCode());
		result = prime * result + ((canceledTime == null) ? 0 : canceledTime.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (cancelUser == null) {
			if (other.cancelUser != null)
				return false;
		} else if (!cancelUser.equals(other.cancelUser))
			return false;
		if (canceledTime == null) {
			if (other.canceledTime != null)
				return false;
		} else if (!canceledTime.equals(other.canceledTime))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (status != other.status)
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
