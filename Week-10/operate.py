def operate(func, a, b):
    return func(a, b)

def add(x, y): return x + y
def sub(x, y): return x - y
def mul(x, y): return x * y
def div(x, y): return x / y if y != 0 else "Division by zero not allowed"

print("Addition:", operate(add, 10, 5))
print("Subtraction:", operate(sub, 10, 5))
print("Multiplication:", operate(mul, 10, 5))
print("Division:", operate(div, 10, 5))
