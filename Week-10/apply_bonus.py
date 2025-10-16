def apply_bonus(func, salary):
    return func(salary)
def festival_bonus(salary):
    bonus = 0.10 * salary
    return salary + bonus

def performance_bonus(salary):
    bonus = 0.20 * salary
    return salary + bonus
updated_salary = apply_bonus(performance_bonus, 50000)
print("Updated Salary:", updated_salary)
