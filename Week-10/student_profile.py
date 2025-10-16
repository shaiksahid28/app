def student_profile(name):
    marks = {}
    def add_marks(subject, mark):
        marks[subject] = mark
        avg = sum(marks.values()) / len(marks)
        print(f"{name}'s updated average: {avg}")
    return add_marks

student1 = student_profile("Durga")
student1("Math", 85)
student1("Science", 90)
student1("English", 80)
