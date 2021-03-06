package app.mobius.signUp.service

import app.mobius.signUp.data.repository.PersonJpaRepository
import app.mobius.domain.entity.Person
import app.mobius.jsonApi.JsonApiMapper
import app.mobius.jsonApi.model.JsonApiResource
import app.mobius.loggerFor
import app.mobius.signUp.infrastructure.dto.PersonDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
@Service
class SignUpService {

    /**
     * Get the bean called personRepository, which is auto-generated by Spring,
     * we will use it to handle the data
     */
    @Autowired
    private lateinit var personJpaRepository: PersonJpaRepository

    private val LOG = loggerFor(SignUpService::class.java)

    fun getPeople() : List<Person> {
        return personJpaRepository.findAll()
    }

    fun getPersonById() : Person {
        return Person() //TODO
    }

    fun createPerson(personJsonApi: JsonApiResource) : String {
        LOG.info("feature-sign-up | Create Person")

        val personDto = JsonApiMapper.mapJsonApiResourceToDto(
                jsonApiResource = personJsonApi,
                    dtoType = PersonDto::class.java
        )

        val person = mapDtoToEntity(personDto)

        return try {
            personJpaRepository.save(person)
            "Success when person is saved"
        } catch (e: Exception) {
            e.printStackTrace()
            LOG.info("feature-sign-up | ERROR-0 | ${e.message}")
            throw Exception()
        }
    }

//    TODO: Migrate to generic
    private fun mapDtoToEntity(personDto: PersonDto) = Person(
        username = personDto.username,
        profile = personDto.profile,
        setting = personDto.setting
    )

    fun convertFromEntityToDto(person: Person) : PersonDto {
        return PersonDto(
                username = person.username,
                profile = person.profile,   
                setting = person.setting
        )
    }


}