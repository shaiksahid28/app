def filter_students(criteria, students):
    return [student for student in students if criteria(student)]

students = [
    {"name": "Durga", "cgpa": 8.5},
    {"name": "Amit", "cgpa": 7.8},
    {"name": "Sneha", "cgpa": 9.1},
    {"name": "Ravi", "cgpa": 6.9}
]

high_cgpa_students = filter_students(lambda s: s["cgpa"] > 8, students)

print("Students with CGPA > 8:")
for s in high_cgpa_students:
    print(s["name"], "-", s["cgpa"])