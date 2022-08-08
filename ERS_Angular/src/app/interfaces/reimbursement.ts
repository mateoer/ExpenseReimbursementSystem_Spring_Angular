export interface Reimbursement {
    
    rei_amount: number,
    rei_description: string,    
    reiType: ReiType,
    reiStatus: Status.PENDING    
}

export enum ReiType {
    LODGING,
    GAS,
    FOOD,
    OTHER
}

export enum Status {
    PENDING,
    APPROVED,
    DENIED
}
