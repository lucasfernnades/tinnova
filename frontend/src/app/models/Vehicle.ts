export interface Vehicle<T> {

    id: number,
    success: boolean,
    message: string,
    data: {

        id: number,
        model: string,
        make: string,
        releaseYear: number,
        color: string,
        sold: boolean,
        created: string,
        updated: null
    }

}