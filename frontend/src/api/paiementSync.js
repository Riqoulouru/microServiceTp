import { URL_API } from '@/main'

export default class PaiementSync {

    static addNewPaiement (panier, token) {

        let value = panier.map(ele => ele.price).reduce((partialSum, a) => partialSum + a, 0);
        const paiement = {
            "value": value,
            "date": new Date()
        }

        return fetch(URL_API + '/paiements/save' ,{
            method: "POST",
            body: JSON.stringify(paiement),
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        });
    }


}
