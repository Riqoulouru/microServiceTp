import { URL_API_CLIENT } from '@/main'

export default class ClientSync {

    static processLogin (login, password) {

        const params = new URLSearchParams();
        params.append('login', login);
        params.append('password', password);

        return fetch(URL_API_CLIENT + '/service/login',
            {
                method: "POST",
                body: params,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            });

    }

    static getAllClient (options) {

        const param = new URLSearchParams({
            "page": options.page - 1,
            "numberPerPage": options.itemsPerPage,
            "sortedBy": options.sortBy,
            "isSortedDesc": options.sortDesc,
            "search": search
        })

        return fetch(URL_API_CLIENT + '/clients/all?' + param, { method: "GET"});
    }


}