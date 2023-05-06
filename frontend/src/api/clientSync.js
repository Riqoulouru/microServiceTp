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

    static getClientByLogin (login, token) {
        return fetch(URL_API + '/clients/one/' + login, {
            method: "GET",
            headers: {
                Authorization: token
            }
        });
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

    static removeToWishList (login, idProduit, token) {
        return fetch(URL_API + '/clients/remove/wish/' + login + "/" + idProduit, {
            method: "DELETE",
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        });
    }

    static removeToCart (login, idProduit, token) {
        return fetch(URL_API + '/clients/remove/cart/' + login + "/" + idProduit, {
            method: "DELETE",
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        });
    }

    static resetCart (login, token) {
        return fetch(URL_API + '/clients/reset/cart/' + login, {
            method: "DELETE",
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        });
    }


}
