def discount_calculator(rate):
    def apply_discount(price):
        return price - (price * rate)
    return apply_discount

ten_percent = discount_calculator(0.1)
print("Discounted price:", ten_percent(2000))
