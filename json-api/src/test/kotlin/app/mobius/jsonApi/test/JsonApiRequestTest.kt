package app.mobius.jsonApi.test

import app.mobius.io.ParentPathFile
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.model.request.Data
import app.mobius.jsonApi.model.request.JsonApiRequest
import app.mobius.jsonApi.model.request.Links
import app.mobius.jsonApi.model.request.Relationship
import app.mobius.jsonApi.test.model.request.AttributesMock
import app.mobius.jsonApi.test.model.request.DataAtomicMock
import app.mobius.jsonApi.test.model.request.RelationshipMock
import app.mobius.jsonApi.test.model.request.RelationshipsMock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.util.*

class JsonApiRequestTest {

    private fun provideData(type: String = "",
                            id: UUID? = null,
                            attributes: Map<String, Any> = mapOf(),
                            relationships: List<Relationship> = listOf(),
                            links: Links? = null
    ) = Data(type,id, attributes, relationships, links)

    private fun provideJsonApiRequest(data: List<Data> = listOf()) =
            JsonApiRequest(data = data)

    private fun provideRelationship(
            relationship: Map<String, Data> = mapOf()
    ) = Relationship(relationship)


    @Test
    fun `1 - When write the attributes from json to kt, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = JsonApi.MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/generated/request/attributes.json",
                    valueType = AttributesMock::class.java
            )
        }
    }

    @Test
    fun `2 - When write relationship with a data atomic mock from KT to JSON, Then the expected is equal to the actual from JSON as KT`() {
//       Given
        val expectedRelationshipWithDataAtomicMock = RelationshipMock(
                mapOf("data" to DataAtomicMock("profile"))
        )

//        When
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/relationship/relationshipWithDataAtomicMock.json",
                value = expectedRelationshipWithDataAtomicMock
        )
        val actualRelationshipWithDataAtomicMock = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/relationship/relationshipWithDataAtomicMock.json",
                valueType = RelationshipMock::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipWithDataAtomicMock, actualRelationshipWithDataAtomicMock)
    }

    @Test
    fun `3 - When write some relationships with a data atomic mock from KT as JSON, Then the expected is equal to the actual from JSON as KT`() {
//        Given
        val actualRelationshipsWithDataAtomicMock = RelationshipsMock(
                relationships = listOf(
                        RelationshipMock(
                                mapOf("data" to DataAtomicMock("profile"))
                        ),
                        RelationshipMock(
                                mapOf("data" to DataAtomicMock("setting"))
                        )
                )
        )

//        When
        assertDoesNotThrow {
            JsonApi.writeKtAsJsonToFile(
                    moduleName = JsonApi.MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/generated/request/relationship/relationshipsWithDataAtomicMock.json",
                    value = actualRelationshipsWithDataAtomicMock
            )
        }
        val expectedRelationshipsWithDataAtomicMock = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/relationship/relationshipsWithDataAtomicMock.json",
                valueType = RelationshipsMock::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipsWithDataAtomicMock, actualRelationshipsWithDataAtomicMock)
    }

    @Test
    fun `4 - When write some links from KT as JSON, Then the expected is equal to the actual from JSON as KT`() {
        val expectedLinks = Links()

        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/links.json",
                value = expectedLinks
        )
        val actualLinks = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/links.json",
                valueType = Links::class.java
        )

//        Then
        Assertions.assertEquals(expectedLinks, actualLinks)
    }

    @Test
    fun `5 - When write a data without relationships from KT as JSON, Then the expected is equal to the actual from JSON as KT `() {
        val expectedData = provideData()

        assertDoesNotThrow {
            JsonApi.writeKtAsJsonToFile(
                    moduleName = JsonApi.MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/generated/request/data/dataWithoutRelationships.json",
                    value = expectedData
            )
        }
        val actualData = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/data/dataWithoutRelationships.json",
                valueType = Data::class.java
        )

//        Then
        Assertions.assertEquals(expectedData, actualData)
        assert(actualData.relationships.isEmpty())
    }

    @Test
    fun `5B - When write a data with relationships from KT as JSON, Then the expected is equal to the actual from JSON as KT and relations is not empty`() {
        val expectedData = provideData(
                relationships = listOf(
                        provideRelationship(),
                        provideRelationship()
                )
        )

        assertDoesNotThrow {
            JsonApi.writeKtAsJsonToFile(
                    moduleName = JsonApi.MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/generated/request/data/dataWithRelationships.json",
                    value = expectedData
            )
        }
        val actualData = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/data/dataWithRelationships.json",
                valueType = Data::class.java
        )

//        Then
        Assertions.assertEquals(expectedData, actualData)
        assert(actualData.relationships.isNotEmpty())
    }

    @Test
    fun `6 - When write a jsonApiRequest without data from KT to JSON, Then data of JSON is empty`() {
//        Given
        val jsonApiRequest = provideJsonApiRequest()

//        When
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/jsonApiRequest/emptyData.json",
                value = jsonApiRequest
        )
        val jsonApiRequestActual = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/jsonApiRequest/emptyData.json",
                valueType = JsonApiRequest::class.java
        )

//        Then
        assert(jsonApiRequestActual.data.isEmpty())
        Assertions.assertEquals(jsonApiRequest, jsonApiRequestActual)
    }

    @Test
    fun `7 - When write a jsonApiRequest with a data from KT to JSON, Then data of JSON is not empty`() {
//        Given
        val jsonApiRequest = provideJsonApiRequest(data = listOf(Data()))

//        When
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/jsonApiRequest/withData.json",
                value = jsonApiRequest
        )
        val jsonApiRequestActual = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/jsonApiRequest/withData.json",
                valueType = JsonApiRequest::class.java
        )

//        Then
        assert(jsonApiRequestActual.data.isNotEmpty())
        Assertions.assertEquals(jsonApiRequest, jsonApiRequestActual)
    }

    @Test
    fun `8 - When write a jsonApiRequest with a data and relationships from KT to JSON, Then data and relationships of JSON is not empty`() {
//        Given
        val expectedJsonApiRequest = provideJsonApiRequest(
                data = listOf(
                        provideData(
                                relationships = listOf(
                                        provideRelationship(),
                                        provideRelationship(),
                                )
                        )
                )
        )

//        When
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/jsonApiRequest/withDataAndRelationships.json",
                value = expectedJsonApiRequest
        )
        val jsonApiRequestActual = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/jsonApiRequest/withDataAndRelationships.json",
                valueType = JsonApiRequest::class.java
        )

//        Then
        assert(jsonApiRequestActual.data.isNotEmpty())
        assert(jsonApiRequestActual.data.isNotEmpty())
        Assertions.assertEquals(expectedJsonApiRequest, jsonApiRequestActual)
    }

}