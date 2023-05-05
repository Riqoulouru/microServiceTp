import { URL_API } from '@/main'

export default class ClientSync {

    static processLogin (login, password) {

        const params = {
            "username": login,
            "password": password
        }

        return fetch(URL_API + '/clients/token',
            {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(params)

            });

    }

    static processSignUp (login, password, firstname, lastname) {

        const params = {
            "login": login,
            "password": password,
            "firstname": firstname,
            "lastname": lastname
        }

        return fetch(URL_API + '/clients/register',
            {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(params)

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

        return fetch(URL_API + '/clients/all?' + param, { method: "GET"});
    }

    static addProductToWishList (login, idProduit, token) {
        return fetch(URL_API + '/clients/update/wish/' + login + "/" + idProduit, {
            method: "PATCH",
            headers: {
                Authorization: token
            }
        });
    }


    static addProductToCart (login, idProduit, token) {
        return fetch(URL_API + '/clients/update/cart/' + login + "/" + idProduit, {
            method: "PATCH",
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        });
    }


}
