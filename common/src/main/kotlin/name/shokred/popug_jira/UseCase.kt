package name.shokred.popug_jira

interface UseCase<T, R> where T : UseCaseDto {

    fun invoke(dto: T): R
}
