export interface ReiListMap {

    userId : number,        
    firstName: string,
    lastName: string,
    username: string,    
    userRole: string,

    reiId : number;
    rei_amount : number;
    rei_description : string;
    reiStatus: string;
    reiType : string;
    reiAuthor : number;
    rei_resolver : number;
    rei_submitteDate : Date;
    rei_resolvedDate : Date;
    receiptPicName: string;
}
