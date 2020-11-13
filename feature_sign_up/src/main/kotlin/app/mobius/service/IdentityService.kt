package app.mobius.service

import app.mobius.data.dao.PersonRepository
import app.mobius.data.repository.PersonResourceRepository
import app.mobius.domain.entity.Person
import io.crnk.core.queryspec.QuerySpec
import io.crnk.core.resource.list.ResourceList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import java.util.*


@Service
class PersonService {

    /**
     * Get the bean called personRepository, which is auto-generated by Spring,
     * we will use it to handle the data
     */
    @Autowired
    private lateinit var personRepository: PersonRepository

    @Autowired
    private lateinit var personResourceRepository: PersonResourceRepository

 /*   fun getPeople() : List<Person> {
        return personRepository.findAll()
    }*/

    @PostMapping("/all3")
    fun getPeopleResource() : ResourceList<Person>? {
        val querySpec1 = QuerySpec(Person::class.java)
        return personResourceRepository.findAll(querySpec1)
    }

    fun getPersonById() : Person {
        return Person() //TODO
    }

    fun createPerson(person: Person) : String {
        personRepository.save(person)
        return "Saved"
    }

}
