import { Attachment } from "./attachment"

export interface AuthUser {
  id?: number
  name?: string
  username: string
  password: string
  profilePicture?: Attachment
}
