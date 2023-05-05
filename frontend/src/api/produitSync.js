import { URL_API } from '@/main'

export default class ProduitSync {

    static getAllProduitByCategoryForHomePage (token) {
        return fetch(URL_API + '/produits/home/example',{
            method: "GET",
            headers: {
                 Authorization: token
            }
        });
    }

    static getAllBySearch (options, search, token) {
        const param = new URLSearchParams({
            "page": options.page - 1,
            "numberPerPage": options.itemsPerPage,
            "sortedBy": options.sortBy,
            "isSortedDesc": options.sortDesc,
            "search": search
        })
        return fetch(URL_API + '/produits/home/search?' + param,{
            method: "GET",
            headers: {
                 Authorization: token
            }
        });
    }

}
