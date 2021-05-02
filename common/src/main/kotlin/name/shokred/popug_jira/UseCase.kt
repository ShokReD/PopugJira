package name.shokred.popug_jira

interface UseCase<T> where T : UseCaseDto {

    fun invoke(dto: T)
}
