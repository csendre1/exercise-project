import { Attachment } from "./attachment"

export interface AuthUser{
    name ?: string
    username : string
    password : string
    profilePicture ?: Attachment
}
