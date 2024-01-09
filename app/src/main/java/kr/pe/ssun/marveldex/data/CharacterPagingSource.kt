package kr.pe.ssun.marveldex.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.first
import kr.pe.ssun.marveldex.data.model.Character
import kr.pe.ssun.marveldex.data.repository.MarvelRepository
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    val repository: MarvelRepository
) : PagingSource<Int, Character>() {

    companion object {
        const val size = 20;
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Character> {
        return try {
            // Start refresh at page 0 if undefined.
            val nextPageNumber = params.key ?: 0
            val characters = repository.getCharacters(
                limit = size,
                offset = nextPageNumber * size
            ).first() ?: listOf()
            LoadResult.Page(
                data = characters,
                prevKey = null, // Only paging forward
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(Throwable(e.message))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}