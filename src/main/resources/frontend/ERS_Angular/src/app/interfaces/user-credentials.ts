export interface UserCredentials {
    user: {
        userId : number,
        username: string,
        password: string,
        firstName: string,
        lastName: string,
        email: string,
        userRole: string,
        profilePicName: string
    },
    found: boolean
}
