import { URL_API } from '@/main'

export default class CommandeSync {

    static validatePanier (login, idProduits, idPaiement, token) {
        return fetch(URL_API + '/commandes/validate/' + login + "/" + idPaiement ,{
            method: "POST",
            body: JSON.stringify(idProduits),
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        });
    }


}
