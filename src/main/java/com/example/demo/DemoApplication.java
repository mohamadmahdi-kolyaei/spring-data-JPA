package com.example.demo;

import com.github.javafaker.Faker;
import com.github.javafaker.Number;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	//we can have some cod running after application
	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository ,
			StudentIdCardRepository studentIdCardRepository
			){
		return args -> {
//			Student maria = new Student(
//					"Maria" ,
//					"Jones" ,
//					"maria.jones@amigoscode.edu" ,
//					21
//			);
//
//			Student maria2 = new Student(
//					"Maria" ,
//					"Jones" ,
//					"maria2.jones@amigoscode.edu" ,
//					25
//			);
//
//			Student ahmed = new Student(
//					"Ahmed" ,
//					"Ali" ,
//					"ahmed.ali@amigoscode.edu" ,
//					18
//			);
//			System.out.println("adding maria and ahmed");
//			studentRepository.saveAll(List.of(maria,ahmed ,maria2));
//
//			System.out.println("number of students : ");
//			System.out.println(studentRepository.count());
//
//			studentRepository.findById(2L).ifPresentOrElse(
//					student -> {
//						System.out.println(student);
//					} , () -> System.out.println("student with id 2 not found")
//			);
//
//			studentRepository.findById(3L).ifPresentOrElse(
//					System.out::println, () -> System.out.println("student with id 3 not found")
//			);
//
//			System.out.println("select all students  :    ");
//			List<Student> students = studentRepository.findAll();
//			students.forEach(System.out::println);
//			// above foreach is concise of this foreach
////			for (Student student : students) {
////				System.out.println(student);
////			}
////			System.out.println("delete maria :    ");
////			studentRepository.deleteById(1L);
//
//			System.out.println("number of students : ");
//			System.out.println(studentRepository.count());
//
//			System.out.println("find a student by email :   ");
//			studentRepository.findStudentByEmail("ahmed.ali@amigoscode.edu")
//					.ifPresentOrElse(System.out::println , () -> System.out.println("student with this email not found"));
//
//			System.out.println("findStudentByFirstNameEqualsAndAgeEquals   :   ");
//			studentRepository.findStudentByFirstNameEqualsAndAgeGreaterThanEqual("maria" , 21)
//					.forEach(System.out::println);
//
//			System.out.println("deleting maria 2  ");
//			System.out.println(studentRepository.deleteStudentById(3L));
//
////			studentRepository.deleteStudentByFirstNameContainsIgnoreCase("ahmed");
//
////			List<Student> age = studentRepository.findByAge(21);
////			age.forEach(System.out::println);   until now part1


//			generateRandomStudents(studentRepository);
////			studentRepository.findAll(Sort.by(Sort.Direction.DESC , "firstName"))
////					.forEach(student -> System.out.println(student.getFirstName()));
//
////			studentRepository.findAll(Sort.by("firstName").ascending().and(Sort.by("age").descending()))
////					.forEach(student -> System.out.println(student.getFirstName() + "    " + student.getAge()));
//
//			PageRequest pageRequest =PageRequest.of(  // we want to share just some student not all of them, so we use pagination
//					0 ,
//					5 ,
//					Sort.by("firstName").ascending());
//			Page<Student> page = studentRepository.findAll(pageRequest);
//			page.toList().forEach(System.out::println); until now part2



			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@amigoscod.edu" , firstName , lastName);
			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(17,50)
			);

			StudentIdCard studentIdCard = new StudentIdCard("123456789" , student);
//			studentIdCardRepository.save(studentIdCard);

//			studentIdCardRepository.findById(1L).ifPresentOrElse(System.out::println,() -> System.out.println("not found"));
//
//			studentRepository.findById(1L).ifPresentOrElse(System.out::println,() -> System.out.println("fuck that shit"));

//			studentIdCardRepository.deleteById(1L);   when i delete this studentId card student wont delete


			student.addBook(new Book("Clean code ", LocalDateTime.now().minusDays(4)));
			student.addBook(new Book("Think and grow ", LocalDateTime.now()));
			student.addBook(new Book("Spring data JPA ", LocalDateTime.now().minusYears(1)));  // if you see we don't have repository for book:)
//			studentIdCardRepository.save(studentIdCard);
			// for saving we can set studentId and save student

//			student.enrolToCourse(new Course("Computer science" , "IT"));
//			student.enrolToCourse(new Course("Amigoscode spring data JPA" , "IT"));

			student.addEnrolment(new Enrolment(new EnrolmentId(1L, 1L),student,new Course("Computer science" , "IT") , LocalDateTime.now()));
			student.addEnrolment(new Enrolment(new EnrolmentId(1l,2L), student,new Course("Amigoscode spring data JPA" , "IT") , LocalDateTime.now().minusDays(18)));

			student.setStudentIdCard(studentIdCard);
			studentRepository.save(student);  // we no longer need studentIdRepository because everything is going through cascade

			studentRepository.findById(1L)
					.ifPresent( s -> {
						System.out.println("fetch book lazy ...");
						List<Book> books = student.getBooks();
						books.forEach(b -> {
							System.out.println(s.getFirstName() + " borrowed " + b.getBookName());  // the books won't join in loading and as soon as we say get book it goes to the database and fetch books
						}); // if you change the fetch type to lazy to eager you can see the books table will join
						// the key here is you should start with lazy because not always you want to fetch for example in this case books , because if this contains a lot of data it can slow down you application

					});
//			studentRepository.findById(1L).ifPresent(s -> System.out.println(s));

		};
	}

	private static void generateRandomStudents(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for (int i = 0 ; i < 20  ; i++){
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@amigoscod.edu" , firstName , lastName);
			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(17 , 50)
			);
			studentRepository.save(student);
		}
	}

}
