export interface UserCredentials {
    user: {
        userId : number,
        username: string,
        password: string,
        firstName: string,
        lastName: string,
        email: string,
        userRole: string
    },
    found: boolean
}
